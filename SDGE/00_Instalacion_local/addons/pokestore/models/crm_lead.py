from odoo import fields, models
class CrmLead(models.Model):
    _inherit = "crm.lead"

    pokemon_wanted = fields.Char(string="Pokémon buscado")
    collection_interest_id = fields.Many2one(
        "pokestore.collection",
        string="Colección de interés",
        ondelete="set null",
    )
    estimated_figure_count = fields.Integer(
        string="Número estimado de figuras",
        default=1,
    )
