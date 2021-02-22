# -*- coding: utf-8 -*-
"""

AirplaneService class - This class holds the method related to Airplanes manipulations.

"""

from server.main.models.airplane import Airplane
from server.main.services import SQLAlchemyService


class AirplaneService(SQLAlchemyService):
    __model__ = Airplane

    def __init__(self):
        self.parentClassRef = super(AirplaneService, self)

