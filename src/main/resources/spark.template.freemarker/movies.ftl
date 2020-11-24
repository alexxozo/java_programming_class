<html>
    <head>
        <title>Movies Library</title>
        <link rel="stylesheet" href="/style.css">
    </head>
    <body>
        <header>Books Library</header>

        <#list movies as movie>
            <section>
                <p>Movie: "${movie.title}"</p>
                <p>Year: "${movie.year}"</p>
                <p>Director: "${movie.director}"</p>
                <a href="/movie/">See details</a>
            </section>
        </#list>

        <footer>Java Programming - Harbour.Space University</footer>
    </body>
</html>