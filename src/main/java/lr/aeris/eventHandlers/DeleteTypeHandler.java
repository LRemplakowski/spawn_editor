package lr.aeris.eventHandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import lr.aeris.controller.TypePageController;
import lr.aeris.requests.DeleteTypeRequest;
import lr.aeris.service.SpawnTypeService;

public class DeleteTypeHandler implements EventHandler<ActionEvent> {
    private final TypePageController typePageController;
    private final SpawnTypeService typeService;

    public DeleteTypeHandler(TypePageController typePageController, SpawnTypeService typeService) {
        this.typePageController = typePageController;
        this.typeService = typeService;
    }

    @Override
    public void handle(ActionEvent event) {
        String typeToDelete = typePageController.getQueryTypeName().getText();
        DeleteTypeRequest request = new DeleteTypeRequest.DeleteTypeRequestBuilder(typeToDelete).build();
        typeService.deleteType(request);
        typePageController.queryTypeNameModified();
    }
}
