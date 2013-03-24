## Android Dynamic Loader
Sample projects to load dex file (dalvik executable code) and res file (android resources, probably packaged in the same apk file) into the current android runtime environment.

## ActivityLoader
You can develop a new version of SampleActivity in project ActivityDeveloper, generate a apk to ActivityLoader/assets/apks/ and launch that SampleActivity in ActivityLoader.

The SampleActivity cannot have it's own resources.

## FragmentLoader
You can develop com.dianping.example.SampleFragment in project FragmentDeveloper, generate a apk to FragmentLoader/assets/apks/ and launch that apk in FragmentLoader.

The SampleFragment can also load resources.

## plframework
It's forked from activityLoader project. use a little tricky method to implement supporting own resource in pluginActivity.

The pluginActivity should inherit from BaseActivity and set the Config file in source code. The project pluginActivity is a example of the plframework.

PS: u can also run pluginActivity seperately.

