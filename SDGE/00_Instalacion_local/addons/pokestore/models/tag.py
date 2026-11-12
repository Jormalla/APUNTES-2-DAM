from odoo import fields, models
class PokeStoreTag(models.Model):
    _name = "pokestore.tag"
    _description = "Etiqueta de la figura"
    _order = "name"
    name = fields.Char(string="Nombre", required=True)
