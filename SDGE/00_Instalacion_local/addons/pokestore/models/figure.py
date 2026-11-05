from odoo import fields, models
class PokeStoreFigure(models.Model):
    _name = "pokestore.figure"
    _description = "Figura coleccionable de la franquicia pokémon"
    _order = "name"
    name = fields.Char(string="Nombre", required=True)
    pokemon = fields.Char(string="Pokémon", required=True)
    rarity = fields.Selection(
        [
            ("normal", "Normal"),
            ("rare", "Rara"),
            ("exclusive", "Exclusiva"),
        ],
        string="Rareza",
        default="normal",
        required=True,
    )
    price = fields.Float(string="Precio", required=True)
    stock = fields.Integer(string="Stock", default=0)
    active = fields.Boolean(default=True)

    collection_id = fields.Many2one(
        "pokestore.collection",
        string="Colección",
        ondelete="restrict",
    )