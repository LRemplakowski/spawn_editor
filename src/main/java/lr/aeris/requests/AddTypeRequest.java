package lr.aeris.requests;

public class AddTypeRequest {
    private String typeId;

    private AddTypeRequest(AddTypeRequestBuilder builder) {
        this.typeId = builder.typeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public static class AddTypeRequestBuilder {
        //required
        private String typeId;

        public AddTypeRequestBuilder(String typeId) {
            this.typeId = typeId;
        }

        public AddTypeRequest build() {
            return new AddTypeRequest(this);
        }
    }
}
