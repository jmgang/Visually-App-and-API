import requests

from asl_search_app.rag.utils import get_gcloud_access_token

def search(query):
    url = "https://discoveryengine.googleapis.com/v1alpha/projects/351362467728/locations/global/collections/default_collection/dataStores/visually-app-startasl-learning-materials-cloud-st_1714274252270/servingConfigs/default_search:search"
    headers = {
        "Authorization": "Bearer {}".format(get_gcloud_access_token()),
        "Content-Type": "application/json"
    }
    data = {
        "query": query,
        "pageSize": 10,
        "queryExpansionSpec": {"condition": "AUTO"},
        "spellCorrectionSpec": {"mode": "AUTO"}
    }
    response = requests.post(url, json=data, headers=headers)
    return response.json()