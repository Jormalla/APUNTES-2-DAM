#!/usr/bin/env bash
set -Eeuo pipefail

DB="${1:-aulatec}"
MODULE="${2:-pokestore}"
SERVICE="${ODOO_SERVICE:-web}"

echo "=========================================="
echo " Actualización de módulo Odoo"
echo "=========================================="
echo "Base de datos : $DB"
echo "Módulo        : $MODULE"
echo "Servicio Odoo : $SERVICE"
echo

if ! command -v docker >/dev/null 2>&1; then
    echo "ERROR: no se encuentra Docker."
    exit 1
fi

if ! docker compose version >/dev/null 2>&1; then
    echo "ERROR: Docker Compose no está disponible."
    exit 1
fi

if [ ! -f "docker-compose.yml" ] && [ ! -f "docker-compose.yaml" ] && [ ! -f "compose.yml" ] && [ ! -f "compose.yaml" ]; then
    echo "ERROR: ejecuta este script desde la carpeta donde está el archivo Docker Compose."
    exit 1
fi

if ! docker compose config --services | grep -qx "$SERVICE"; then
    echo "ERROR: no existe el servicio '$SERVICE' en este Docker Compose."
    echo
    echo "Servicios disponibles:"
    docker compose config --services
    exit 1
fi

restart_odoo() {
    echo
    echo "Volviendo a arrancar Odoo..."
    docker compose start "$SERVICE" >/dev/null 2>&1 || true
}

# Si algo falla después de detener Odoo, intentamos dejarlo arrancado.
trap restart_odoo EXIT

echo "[1/3] Parando Odoo..."
docker compose stop "$SERVICE"

echo
echo "[2/3] Actualizando '$MODULE' en la base '$DB'..."
docker compose run --rm "$SERVICE" \
    odoo \
    -d "$DB" \
    -u "$MODULE" \
    --stop-after-init \
    --no-http

echo
echo "[3/3] Arrancando Odoo..."
trap - EXIT
docker compose start "$SERVICE"

echo
echo "=========================================="
echo " Actualización completada correctamente"
echo "=========================================="
