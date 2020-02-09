"""
    Filename: 
    Description:
    Author: Domhnall Boyle
    Maintained by: Domhnall Boyle
    Email: domhnallboyle@gmail.com
    Python Version: 3.6
"""
from flask_restplus import Namespace, Resource

about_namespace = Namespace('About', path='/about', description='Information about the app',)


@about_namespace.route('/')
class About(Resource):

    def get(self):
        from main import api

        return {
            'title': api.title,
            'version': api.version,
            'description': api.description
        }
