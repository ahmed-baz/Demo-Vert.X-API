package demo.vertx.app.bll.transformer;

public interface CommonTransformer<Entity, Bean> {

    public Bean fromEntityToBean(Entity entity);

    public Entity fromBeanToEntity(Bean bean);
}
