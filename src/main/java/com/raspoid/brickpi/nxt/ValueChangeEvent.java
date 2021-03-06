/*******************************************************************************
 * Copyright (c) 2016 Julien Louette & Gaël Wittorski
 * 
 * This file is part of Raspoid.
 * 
 * Raspoid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Raspoid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Raspoid.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.raspoid.brickpi.nxt;

/**
 * POJO holding the values of a change value event
 * 
 * @author Julien Louette &amp; Ga&euml;l Wittorski
 * @version 1.0
 */
public class ValueChangeEvent {
    
    /**
     * Contains the old value which was set before the event
     */
    private int oldValue;
    
    /**
     * Contains the new value which is set after the event
     */
    private int newValue;

    /**
     * Creates a POJO holding the event values
     * @param oldValue value before the event
     * @param newValue value after the event
     */
    public ValueChangeEvent(int oldValue, int newValue) {
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /**
     * Get the old value
     * @return the old value which was set before the event
     */
    public int getOldValue() {
        return oldValue;
    }

    /**
     * Get the new value
     * @return the new value which is set after the event
     */
    public int getNewValue() {
        return newValue;
    }
    
}
