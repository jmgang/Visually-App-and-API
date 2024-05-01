from rest_framework.response import Response
from rest_framework.decorators import api_view
from sl_translation_app.api.translate import generate_sign_language_from_bytes, generate_sign_language_from_gs
from sl_translation_app.api.utils import get_gs_uri

@api_view(['POST'])
def translate_sign_language(request):
    video_bytes = request.data.get('video_bytes', None)
    object_name = request.data.get('object_name', None)

    if video_bytes is None and object_name is None:
        return Response({'error': 'Video bytes or Object Name not provided'}, status=400)

    if video_bytes:
        actions, sign_language = generate_sign_language_from_bytes(video_bytes)
    else:
        actions, sign_language = generate_sign_language_from_gs(get_gs_uri('visually-app', object_name))

    print(sign_language)
    return Response({'actions': actions, 'sign_language': sign_language})


