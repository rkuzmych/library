<div class="col-12 col-md-6 col-xl-5 pt-5" style="min-height: 500px;">

    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="exampleFormControlFile1">
                <#if book??>
                    Photo of book is chused
                <#else>
                    Choose the foto of book
                </#if>
            </label>
            <input type="file" class="form-control-file" name="photo">
        </div>

        <div class="form-group">
            <select name="author">
                <#if book??>
                    <option name="allAuthors" value="${authorId}">
                        ${book.author.name}
                    </option>
                </#if>

                <#list authors as author>
                    <option value="${author.id}">${author.name}</option>
                </#list>

            </select>
        </div>

        <div class="form-group">
            <input type="text" class="form-control ${(nameError??)?string('is-invalid', '')}"
                   value="<#if book??>${book.name}</#if>" name="name" placeholder="Name of Book"/>
            <#if nameError??>
                <div class="invalid-feedback">
                    ${nameError}
                </div>
            </#if>
        </div>


        <div class="form-group">
            <select name="genre">
                <#if book??>
                    <option value="${genreId}">
                        ${book.genre.type}
                    </option>
                </#if>

                <#list genres as genre>
                    <option value="${genre.id}">${genre.type}</option>
                </#list>
            </select>
        </div>


        <div class="form-group">
            <input type="number" class="form-control ${(pageCountError??)?string('is-invalid', '')}"
                   value="<#if book??>${book.pageCount}</#if>" name="pageCount" placeholder="Input count of pages"
                   required/>
            <#if pageCountError??>
                <div class="invalid-feedback">
                    ${pageCountError}
                </div>
            </#if>
        </div>


        <div class="form-group">
            <input type="number" class="form-control ${(publishYearError??)?string('is-invalid', '')}"
                   value="<#if book??>${book.publishYear}</#if>" name="publishYear" placeholder="Input year of book"
                   required/>
            <#if publishYearError??>
                <div class="invalid-feedback">
                    ${publishYearError}
                </div>
            </#if>
        </div>


        <#if isEditorForm??>
            <input type="hidden" name="id" value="${book.id}"/>
        </#if>

        <div class="form-group">
            <label for="exampleFormControlFile1">
                <#if book??>
                    PDF of book is chused
                <#else>
                    Choose the PDF of book
                </#if>
            </label>
            <input type="file" class="form-control-file" name="pdf">
        </div>
        <#--csrf-->
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Save Book</button>
    </form>
</div>
