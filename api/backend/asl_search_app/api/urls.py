
from django.urls import path
from asl_search_app.api.views import search_asl_materials

urlpatterns = [
    path('', search_asl_materials)
]
