package org.everit.osgi.ds.annotation.tests.core;

/*
 * Copyright (c) 2011, Everit Kft.
 *
 * All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */

import org.everit.osgi.ds.annotation.api.IHappy;

import aQute.bnd.annotation.component.Activate;
import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(name = "HappyServiceTest")
public class Consumer {

    private IHappy happy;

    @Reference(service = IHappy.class)
    public void setHappy(final IHappy happy) {
        this.happy = happy;
        this.happy.imHappy();
    }

    @Activate
    public void startUp() {
        for (int i = 0; i < 10; i++) {
            happy.imHappy();
            System.out.println("Called " + i + ".");
        }
    }

}
