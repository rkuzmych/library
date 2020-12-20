<#include "security.ftl">
<div class="album py-5" style="background-color: #F0F0F0;">
    <div class="container">
        <div class="row">

            <#list books as book>
            <div class="col-md-4">
                <div class="card mb-4 box-shadow">
                    <#if book.fileName??>
                        <img src="/img/${book.fileName}" class="card-img-top" style="max-height: 415px;">
                    </#if>
                    <div class="card-body">
                        <p class="card-text">${book.name}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group">
                                <#if book.pdfName??>
                                    <a href="/pdf/${book.pdfName}" class="btn btn-sm btn-outline-secondary" role="button">Read book</a>
                                </#if>
                                    <#if isAdmin>
                                        <a href="/edit/${book.id}" class="btn btn-sm btn-outline-secondary" role="button">Edit</a>
                                        <a href="/delete/${book.id}" class="btn btn-sm btn-outline-secondary" role="button">Delete</a>
                                    </#if>
                            </div>
                            <small class="text-muted">${book.pageCount} pages</small>
                        </div>
                    </div>
                </div>
            </div>
            <#else>
                No books aviable
            </#list>
        </div>
    </div>
</div>