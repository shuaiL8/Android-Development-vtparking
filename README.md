# Android-Development
android parking app (Testing)

A parking system that helps people find empty parking spots on campus. User can check in / check out a parking spot by scanning the QR code. The app will notify the user when a new spot become available. The app can also show the history actions the user made. We use flask-restful to implement the network communication, using polling to create long term operation. We use a background service to implement the polling. We use broadcast receiver to trans data from service to main activity We use AsynTask for getting Http Response. We have 2 main UI fragments, 1 camera activity and 3 dialog fragments.
