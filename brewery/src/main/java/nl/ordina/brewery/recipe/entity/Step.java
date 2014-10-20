package nl.ordina.brewery.recipe.entity;

import nl.ordina.brewery.entity.KettleAction;

import java.util.List;

public class Step {

    private List<KettleAction> actions;

    public Step() {
        // Required by JAXB
    }

    public Step(List<KettleAction> actions) {
        this.actions = actions;
    }

    public List<KettleAction> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "Step{" + "actions=" + actions + '}';
    }

}
