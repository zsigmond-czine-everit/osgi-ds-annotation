package org.everit.osgi.ds.annotation.core;

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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.everit.osgi.ds.annotation.api.SimpleService;
import org.everit.osgi.ds.annotation.entity.SimpleEntity;
import org.everit.osgi.transaction.helper.component.api.Callback;
import org.everit.osgi.transaction.helper.component.api.TransactionHelper;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

@Component(name = "SimpleService", provide = SimpleService.class)
public class SimpleServiceImpl implements SimpleService {

    private EntityManager em;

    private TransactionHelper transactionHelper;

    @Override
    public long addSimpleRecord(final String name) {
        return transactionHelper.doInTransaction(new Callback<Long>() {
            @Override
            public Long execute() {
                final SimpleEntity entity = new SimpleEntity();
                entity.setName(name);
                em.persist(entity);
                em.flush();
                return entity.getSimpleId();
            }
        }, true);
    }

    @Override
    public void removeSimpleRecord(final long simpleId) {
        transactionHelper.doInTransaction(new Callback<Void>() {
            @Override
            public Void execute() {
                final SimpleEntity find = em.find(SimpleEntity.class, simpleId);
                if (find == null) {
                    throw new IllegalArgumentException("Not exist the simple record.");
                }
                em.remove(find);
                em.flush();
                return null;
            }
        }, true);

    }

    @Reference(target = "(&(org.apache.aries.jpa.proxy.factory=*)(osgi.unit.name=org.everit.main.pu))")
    public void setEm(final EntityManagerFactory emf) {
        em = emf.createEntityManager();
    }

    @Reference
    public void setTransactionHelper(final TransactionHelper transactionHelper) {
        this.transactionHelper = transactionHelper;
    }

}
