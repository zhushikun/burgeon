<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>玩转spring boot——简单登录认证</title>
</head>
<body>
<h1>玩转spring boot——简单登录认证</h1>
<h4>
    aaaa
</h4>
<h3> 登录用户： + ${name!}"</h3>
<#if userList??>
    <#list userList as user>
        <li>name: ${user.name!}</li>
        <li>gender: ${user.gender!}</li>
        <li>phone: ${user.phone!}</li>
        <li>birthday: ${user.birthday!}</li>
        <li>address: ${user.address!}</li>
    </#list>
</#if>

<form action="user/uploadAvatar" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
    <p>
        Select a file : <input type="file" name="file" size="50" />
    </p>

    <input type="submit" value="Upload It" />
</form>


<a href="/logout">注销</a>
<img src="/picture/20170820.jpg">
<#--<img src="/img/a.jpg">-->
<br />
</body>
</html>
