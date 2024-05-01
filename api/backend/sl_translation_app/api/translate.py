import vertexai
from vertexai.generative_models import GenerativeModel, Part
from vertexai.preview.tuning import sft


def generate_sign_language_from_gs(gs_name):
    vertexai.init(project='axiomatic-treat-421318', location='us-central1')

    vision_model = GenerativeModel(model_name="gemini-1.0-pro-vision-001")
    response = vision_model.generate_content([
        Part.from_uri(
            gs_name,
            mime_type="video/mp4"
        ),
        "Describe what the person is doing with their hands. Be descriptive on the actions as much as possible, and number "
        "the actions as this is important to know the sequence of actions. Label the left and right hand as well. "
        "Refer to the signer as the \"signer\". Limit your sequence of actions of up to 5 only.",
    ])

    print(response)

    if response and response.candidates:
        actions = response.candidates[0].content.parts[0].text
    else:
        actions = "No response"
        return actions, actions

    sft_tuning_job = sft.SupervisedTuningJob(
        "projects/axiomatic-treat-421318/locations/us-central1/tuningJobs/7652610309529534464")
    tuned_model = GenerativeModel(sft_tuning_job.tuned_model_endpoint_name)

    tuned_model_response = tuned_model.generate_content(f'''
    You should classify the text into one of the following classes: ['Help', 'Thank you', 'Deaf', 'Afternoon', 'Good morning']. 
    Your response should only be the classified class.
    You are a sign language expert. Given the following descriptive sequence of actions made by a signer. 
    Determine the meaning of the sign language.

    {actions}
    ''')

    classification = tuned_model_response.candidates[0].content.parts[0].text if tuned_model_response else "No response"

    return actions, classification

def generate_sign_language_from_bytes(video_bytes):
    vertexai.init(project='axiomatic-treat-421318', location='us-central1')

    vision_model = GenerativeModel(model_name="gemini-1.0-pro-vision-001")
    response = vision_model.generate_content([
        Part.from_data(
            video_bytes,
            mime_type="video/mp4"
        ),
        "Describe what the person is doing with their hands. Be descriptive on the actions as much as possible, and number "
        "the actions as this is important to know the sequence of actions. Label the left and right hand as well. "
        "Refer to the signer as the \"signer\". Limit your sequence of actions of up to 5 only.",
    ])

    print(response)

    if response and response.candidates:
        actions = response.candidates[0].content.parts[0].text
    else:
        actions = "No response"
        return actions, actions

    sft_tuning_job = sft.SupervisedTuningJob(
        "projects/axiomatic-treat-421318/locations/us-central1/tuningJobs/7652610309529534464")
    tuned_model = GenerativeModel(sft_tuning_job.tuned_model_endpoint_name)

    tuned_model_response = tuned_model.generate_content(f'''
    You should classify the text into one of the following classes: ['Help', 'Thank you', 'Deaf', 'Afternoon', 'Good morning']. 
    Your response should only be the classified class.
    You are a sign language expert. Given the following descriptive sequence of actions made by a signer. 
    Determine the meaning of the sign language.

    {actions}
    ''')

    classification = tuned_model_response.candidates[0].content.parts[0].text if tuned_model_response else "No response"

    return actions, classification
