package Network;

public abstract class BaseService {

    protected ApiClinet client;

    protected BaseService(ApiClinet client) {
        this.client = client;
    }

}
