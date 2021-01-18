<#include "security.ftl">
<#import "pager.ftl" as p>


<div class="album py-5">
    <div class="container">
        <div class="row">
            <#list page.content as book>
                <div class="col-md-4">
                    <div class="card mb-4 box-shadow">

                        <img src=
                             <#if book.fileName??>
                             "/img/${book.fileName}
                                  </#if>
                            " class="card-img-top">

                        <div class="card-body">
                            <h5 class="card-title">${book.name}</h5>
                            <h6 class="card-subtitle mb-2 text-muted">${book.author.name}</h6>
                            <p class="card-text">${book.genre.type}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <div class="btn-group">
                                    <#if book.pdfName??>
                                        <a href="/pdf/${book.pdfName}" class="btn btn-sm btn-outline-secondary"
                                           role="button">Read book</a>
                                    </#if>
                                    <#if isAdmin>
                                        <a href="/edit/${book.id}" class="btn btn-sm btn-outline-secondary"
                                           role="button">Edit</a>
                                        <a href="/delete/${book.id}" class="btn btn-sm btn-outline-secondary"
                                           role="button">Delete</a>
                                    </#if>
                                </div>
                            <small class="text-muted">${book.pageCount} pages</small>
                        </div>
                    </div>
                </div>
            </div>
            <#else>
                No books available
            </#list>
        </div>
    </div>
</div>
<@p.pager url page />