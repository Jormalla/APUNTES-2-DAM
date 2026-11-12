from odoo import fields, models
from odoo import api, fields, models
from odoo.exceptions import ValidationError
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
            ("exclusive", "Exclusiva")
        ],
        string="Rareza",
        default="normal",
        required=True
    )
    price = fields.Float(string="Precio", required=True)
    stock = fields.Integer(string="Stock", default=0)
    active = fields.Boolean(default=True)

    collection_id = fields.Many2one(
        "pokestore.collection",
        string="Colección",
        ondelete="restrict"
    )

    tag_ids = fields.Many2many(
        "pokestore.tag",
        string="tags",
        ondelete="restrict"
    )

    # Campo calculado
    stock_value = fields.Float(String="Valor en stock", compute="_compute_stock_value", store=True)
    @api.depends("price", "stock")
    def _compute_stock_value(self):
        for figure in self:
            figure.stock_value = figure.price * figure.stock

    # Comprobaciones de errores
    @api.constrains("price", "stock", "collection_id")
    def _check_regla(self):
        for figure in self:
            if figure.price < 0:
                raise ValidationError("El precio no debe ser negativo.")
            if figure.stock < 0:
                raise ValidationError("El stock no puede ser negativo.")
            if figure.rarity == "exclusive" and figure.collection_id == None:
                raise ValidationError("La figura debe estar asociada a una colección.")
