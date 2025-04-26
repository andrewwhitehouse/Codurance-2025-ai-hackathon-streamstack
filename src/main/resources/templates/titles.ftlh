<!DOCTYPE html>
<html>
<head>
    <title>Netflix Titles</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .filters {
            margin-bottom: 20px;
        }
        .filters a {
            margin-right: 10px;
            text-decoration: none;
            color: #0066cc;
        }
        .filters a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Netflix Titles</h1>
    
    <div class="filters">
        <h3>Filters:</h3>
        <a href="/titles?sortBy=year">Sort by Year</a>
        <a href="/titles?sortBy=title">Sort by Title</a>
    </div>

    <table>
        <tr>
            <th>Title</th>
            <th>Type</th>
            <th>Release Year</th>
            <th>Director</th>
            <th>Rating</th>
        </tr>
        <#list titles as title>
        <tr>
            <td>${title.title}</td>
            <td>${title.type}</td>
            <td>${title.releaseYear}</td>
            <td><a href="/titles?director=${title.director}">${title.director}</a></td>
            <td><a href="/titles?rating=${title.rating}">${title.rating}</a></td>
        </tr>
        </#list>
    </table>
</body>
</html> 