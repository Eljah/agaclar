<html>
<body>
<h2>Jersey RESTful Web Application!</h2>
<p><a href="webresources/myresource">Jersey resource</a>
<p>Visit the <a href="http://jersey.java.net">Project Jersey website</a>
for more information on Jersey!
<br>
<form action="webresources/myresource/hipost" method=post>
    <input type="text" name="manname">
</form>

<h1>Upload a File</h1>

<form action="webresources/myresource/upload" method="post" enctype="multipart/form-data">

    <p>
        Select a file : <input type="file" name="file" size="50" />
    </p>

    <input type="submit" value="Upload It" />
</form>

<br>
<form action="webresources/myresource/sergey/hi" method=post>
    <input type="text" name="manname">
</form>

</body>
</html>
