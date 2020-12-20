<select name="authorName">
    <option name="allGenres">All authors</option>
    <#list authors as author>
        <option>${author.name}</option>
    </#list>
</select>