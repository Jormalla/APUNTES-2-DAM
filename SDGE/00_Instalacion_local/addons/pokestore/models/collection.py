from odoo import fields, models
class PokeStoreCollection(models.Model):
    _name = "pokestore.collection"
    _description = "Colección de figuras Pokémon"
    _order = "name"
    
    name = fields.Char(string="Nombre", required=True)
    generacion = fields.Integer(string="Generación", required=True)
    fecha_lanzamiento = fields.Date(string="Fecha de lanzamiento", required=True)
    active = fields.Boolean(default=True)

    collection_id = fields.Many2one(
        "pokestore.collection",
        string="Colección",
        ondelete="restrict"
    )

    figure_ids = fields.One2many(
        "pokestore.figure",
        "collection_id",
        string="Figuras"
    )