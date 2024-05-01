from rest_framework import serializers
from asl_search_app.api.asl_data import ASLData


class ASLSerializer(serializers.Serializer):
    title = serializers.CharField()
    description = serializers.CharField()
    url = serializers.CharField()
    video_url = serializers.CharField()

    def create(self, validated_data):
        return ASLData.from_dict(validated_data)

    def update(self, instance, validated_data):
        instance.title = validated_data.get('title', instance.title)
        instance.description = validated_data.get('description', instance.description)
        instance.url = validated_data.get('url', instance.url)
        instance.video_url = validated_data.get('video_url', instance.video_url)
        return instance
