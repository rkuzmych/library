<#include "security.ftl">
<section class="jumbotron text-center" style="background-color: white">
    <div class="container">
        <h1 class="jumbotron-heading">List of books</h1>
        <p class="lead text-muted">
            Here you can find all books, which you are looking for!
        </p>
    </div>
</section>

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
                                    <a href="/pdf/${book.pdfName}" class="btn btn-sm btn-outline-secondary">Read book</a>
                                </#if>
                                    <#if isAdmin>
                                        <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
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