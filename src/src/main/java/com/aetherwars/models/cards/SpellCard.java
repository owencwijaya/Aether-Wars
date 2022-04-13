package com.aetherwars.models.cards;

import com.aetherwars.interfaces.Prototype;
import com.aetherwars.models.Type;

/**
 * Implementatino for the spell card class
 */
public class SpellCard extends Card implements Prototype<Card> {
    protected int init_duration; // Permanent = 0 / Temporary = depends on dasta
    protected int current_duration;

    /**
     * User-defined constructor for spell card
     * @param id
     * @param name
     * @param type
     * @param description
     * @param image_path
     * @param mana
     * @param duration, 0 if effect's permanent
     */
    public SpellCard(
            int id,
            String name,
            Type type,
            String description,
            String image_path,
            int mana,
            int duration
    ) {
        super(id, name, type, description, mana, image_path);
        this.init_duration = duration;
        this.current_duration = duration;
    }

    public void setDuration(int duration){
        this.current_duration = duration;
    }

    public int getDuration(){
        return this.current_duration;
    }

    @Override
    public Card clone() {
        return new SpellCard(
            this.id,
            this.name,
            this.type,
            this.desc,
            this.image_path,
            this.required_mana,
            this.init_duration
        );
    }
}