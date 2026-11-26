from odoo import fields, models
class ResPartner(models.Model):
    _inherit = "res.partner"

    codigo_coleccionista = fields.Integer(string = "Código collecionista")
    pokemon_favorito = fields.Char(string = "Pokémon favorito")
    nivel_coleccionista = fields.Selection(
        [
            ("inicial", "Inicial"),
            ("avanzado", "Avanzado"),
            ("experto", "Experto")
        ],
        string="Nivel de coleccionista",
        default="inicial",
        required=True
    )

    figure_ids = fields.One2many(
        "pokestore.figure",
        "distribuidor_id",
        string="Figuras"
    )