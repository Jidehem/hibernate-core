/*
 * Copyright (c) 2009, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package org.hibernate.ejb.criteria.expression;

import javax.persistence.criteria.Expression;
import org.hibernate.ejb.criteria.QueryBuilderImpl;

/**
 * Models an ANSI SQL <tt>NULLIF</tt> expression.  <tt>NULLIF</tt> is a specialized <tt>CASE</tt> statement.
 *
 * @author Steve Ebersole
 */
public class NullifExpression<T> extends ExpressionImpl<T> {
	private final Expression<? extends T> primaryExpression;
	private final Expression<?> secondaryExpression;

	public NullifExpression(
			QueryBuilderImpl queryBuilder,
			Class<T> javaType,
			Expression<? extends T> primaryExpression,
			Expression<?> secondaryExpression) {
		super( queryBuilder, (Class<T>)determineType(javaType, primaryExpression) );
		this.primaryExpression = primaryExpression;
		this.secondaryExpression = secondaryExpression;
	}

	public NullifExpression(
			QueryBuilderImpl queryBuilder,
			Class<T> javaType,
			Expression<? extends T> primaryExpression,
			Object secondaryExpression) {
		super( queryBuilder, (Class<T>)determineType(javaType, primaryExpression) );
		this.primaryExpression = primaryExpression;
		this.secondaryExpression = new LiteralExpression( queryBuilder, secondaryExpression );
	}

	private static Class determineType(Class javaType, Expression primaryExpression) {
		return javaType != null ? javaType : primaryExpression.getJavaType();
	}

	public Expression<? extends T> getPrimaryExpression() {
		return primaryExpression;
	}

	public Expression<?> getSecondaryExpression() {
		return secondaryExpression;
	}

}