package lr.aeris.eventHandlers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import lr.aeris.controller.TypePageController;
import lr.aeris.requests.AddTypeRequest;
import lr.aeris.service.SpawnTypeService;

public class AddTypeHandler implements EventHandler<ActionEvent> {
    private final SpawnTypeService typeService;
    private final TypePageController typePageController;

    public AddTypeHandler(TypePageController typePageController, SpawnTypeService typeService) {
        this.typeService = typeService;
        this.typePageController = typePageController;
    }

    @Override
    public void handle(ActionEvent event) {
        String typeName = typePageController.getQueryTypeName().getText();
        AddTypeRequest request = new AddTypeRequest.AddTypeRequestBuilder(typeName).build();
        typeService.saveNewType(request);
        typePageController.queryTypeNameModified();
    }
}
