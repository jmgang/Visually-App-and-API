import subprocess
import os
import json
def get_gcloud_access_token():
    gcloud_path = r"C:\Users\10\AppData\Local\Google\Cloud SDK\google-cloud-sdk\bin\gcloud.cmd"
    result = subprocess.run(
        [gcloud_path, 'auth', 'print-access-token'],
        capture_output=True,
        text=True
    )
    return result.stdout.strip()


def load_json_document(file_name):
    current_script_path = os.path.dirname(__file__)
    project_root = os.path.dirname(current_script_path)
    file_path = os.path.join(project_root, 'resources', file_name)
    with open(file_path, 'r') as file:
        return json.load(file)

def load_json_document_string(file_name):
    current_script_path = os.path.dirname(__file__)
    project_root = os.path.dirname(current_script_path)
    file_path = os.path.join(project_root, 'resources', file_name)
    with open(file_path, 'r') as file:
        return json.dumps(json.load(file))

