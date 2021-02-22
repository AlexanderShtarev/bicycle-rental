<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <form action="<c:url value="/controller"/>" method="POST">
            <input type="hidden" name="command" value="add_category">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="new.category"/></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">

                    <div class="form-group">
                        <label for="category-name" class="col-form-label"><fmt:message key="label.name"/>:</label>
                        <input type="text" class="form-control" required name="categoryName" id="category-name">
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="main.close"/> </button>
                    <input type="submit" class="btn btn-primary" value="<fmt:message key="add.category"/>"/>
                </div>
            </div>
        </form>
    </div>
</div>
