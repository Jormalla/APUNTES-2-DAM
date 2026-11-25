from odoo import fields, models
class SaleOrder(models.Model):
    _inherit = "sale.order"

    embalaje_coleccionista = fields.Boolean(default=False, required=True)
    instrucciones_empaquetado = fields.Char(String="Instrucciones de empaquetado")
    prioridad_preparacion = fields.Selection(
        [
            ("urgente", "Urgente"),
            ("alta", "Alta"),
            ("media", "Media"),
            ("baja", "Baja")
        ],
        string="Prioridad de preparación",
        default="baja",
        required=True
    )

