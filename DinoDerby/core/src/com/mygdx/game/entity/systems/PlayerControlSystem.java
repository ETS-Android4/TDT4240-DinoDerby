package com.mygdx.game.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.SimpleDirectionGestureDetector;
import com.mygdx.game.entity.components.BodyComponent;
import com.mygdx.game.entity.components.PlayerComponent;
import com.mygdx.game.views.PlayScreen;

public class PlayerControlSystem extends IteratingSystem {

    ComponentMapper<PlayerComponent> cmPlayer;
    ComponentMapper<BodyComponent> cmBody;
    OrthographicCamera camera;
    int position = 200;
    float zoom = 0.66f;
    int initVelocity = 20;
    PlayScreen playScreen;

    public PlayerControlSystem(OrthographicCamera camera, PlayScreen playScreen) {
        super(Family.all(PlayerComponent.class).get());

        cmPlayer = ComponentMapper.getFor(PlayerComponent.class);
        cmBody = ComponentMapper.getFor(BodyComponent.class);
        this.camera = camera;

        camera.zoom -= zoom;

        this.playScreen = playScreen;

    }

    @Override
    protected void processEntity(Entity entity, float dt) {
        BodyComponent body = cmBody.get(entity);

        body.body.applyLinearImpulse(500, 0, body.body.getWorldCenter().x, body.body.getWorldCenter().y, true);
        // moves the player towards the right

        if(playScreen.jump > 0) {
            body.body.setLinearVelocity(body.body.getLinearVelocity().x, 60);
            playScreen.jump--;
        } else if (body.body.getPosition().y >= 20) {
            body.body.setLinearVelocity(body.body.getLinearVelocity().x, -50);
        }
        if (body.body.getPosition().y <= 15 && playScreen.jump == 0) {
            body.body.setLinearVelocity(body.body.getLinearVelocity().x, 0);
        }
        // camera follows player
        if (body.body.getPosition().x >= (camera.viewportWidth/2 * zoom)- position)
        camera.translate(((body.body.getPosition().x) - camera.position.x ), 0) ;

        System.out.println("position: "+body.body.getPosition());
        //System.out.printf("test: %s %s\n", body.body.getLinearVelocity(), body.body.getPosition());

    }
}
