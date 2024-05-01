
from django.urls import path
from sl_translation_app.api.views import translate_sign_language

urlpatterns = [
    path('', translate_sign_language)
]
