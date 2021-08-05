# Compose练习项目
根据
[https://github.com/keygenqt/android-GitHubViewer](https://github.com/keygenqt/android-GitHubViewer)改造  
1. room改用objectbox
2. SharedPreferences改用mmkv

# github的token和账号配置
在项目根目录新建 debug.properties 文件  
token申请地址:[https://github.com/settings/tokens](https://github.com/settings/tokens)  
debug.properties 文件内容
```
github_user="账号名"
github_token="github申请的token"
```