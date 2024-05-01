from rest_framework.decorators import api_view
from rest_framework.response import Response

from asl_search_app.api.search_agent import search
from asl_search_app.api.serializers import ASLSerializer


@api_view(['GET'])
def search_asl_materials(request):
    query = request.GET.get('query', 'default_query')
    size = int(request.GET.get('size', 5))

    response_data = search(query)

    results = response_data.get('results', [])[:size]  # Slice the list to include only the first k results
    serializer = ASLSerializer(data=[item['document']['structData'] for item in results], many=True)

    if serializer.is_valid():
        return Response(serializer.validated_data)
    else:
        return Response(serializer.errors, status=400)