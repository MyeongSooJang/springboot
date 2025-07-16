<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
</head>
<body>
    <h2>Hello SpringBoot</h2>
<script>
    const server = new WebSocket("ws://localhost:9999/chatting");
    server.onopen=(data)=>{
        console.log(data);
    }
    server.onmessage=(data)=>{
        console.log(data);
    }
</script>
</body>
</html>
