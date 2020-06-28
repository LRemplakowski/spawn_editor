package lr.aeris.requests;

public class DeleteTypeRequest {
    private String typeId;

    private DeleteTypeRequest(DeleteTypeRequestBuilder builder) {
        this.typeId = builder.typeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public static class DeleteTypeRequestBuilder {
        //required
        private String typeId;

        public DeleteTypeRequestBuilder(String typeId) {
            this.typeId = typeId;
        }

        public DeleteTypeRequest build() {
            return new DeleteTypeRequest(this);
        }
    }
}
