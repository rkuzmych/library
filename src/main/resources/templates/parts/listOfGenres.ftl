<select name="genreType">
            <option name="allGenres">All genres</option>
    <#list genres as genre>
            <option>${genre.type}</option>
    </#list>
</select>
