from odoo import fields, models
class ResPartner(models.Model):
    _inherit = "res.partner"

    codigo_coleccionista = fields.Integer(string = "Código collecionista")
    pokemon_favorito = fields.Char(string = "Pokémon favorito")
    nivel_coleccionista = fields.Integer(string = "Nivel de coleccionista")

    figure_ids = fields.One2many(
        "pokestore.figure",
        "distribuidor_id",
        string="Figuras"
    )