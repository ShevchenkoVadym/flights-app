# -*- coding: utf-8 -*-

from sqlalchemy import inspect

from server.main import db
from sqlalchemy.sql import func


class BaseModel(db.Model):
    """Base data model for all objects"""
    __abstract__ = True

    def __init__(self, *args):
        super().__init__(*args)

    def __repr__(self):
        """Define a base way to print models"""
        return '%s(%s)' % (self.__class__.__name__, {
            column: value
            for column, value in self.as_dict().items()
        })

    def as_dict(self):
        return {c.key: getattr(self, c.key)
                for c in inspect(self).mapper.column_attrs}


class Airplane(BaseModel, db.Model):
    """Model for airplanes table"""
    __tablename__ = "airplanes"

    id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    name = db.Column(db.String(128), nullable=False)
    size = db.Column(db.String(128), nullable=False)
    created_date = db.Column(db.DateTime, nullable=False, default=func.now())

    def __init__(self, name, size):
        super().__init__()
        self.name = name
        self.size = size
