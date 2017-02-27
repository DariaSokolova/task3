package com.epam.mentoring.classloading.security;

import java.security.Permission;

public class ClassPathPermission extends Permission {

	public ClassPathPermission(String name) {
		super(name);
	}

	private static final long serialVersionUID = 1L;
	
	private String action;

	public ClassPathPermission(String target, String action) {
		super(target);
		this.action = action;
	}

	@Override
	public boolean implies(Permission other) {
		boolean result = false;
		if (other instanceof ClassPathPermission) {
			ClassPathPermission p = (ClassPathPermission) other;
			if (action.equals("load")) {
				result = p.action.equals("load") && p.getName().contains(getName());
			} else if (action.equals("avoid")) {
				result = p.action.equals("load") && !p.getName().contains(getName());
			}
		}
		return result;
	}

	@Override
	public String getActions() {
		return action;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassPathPermission other = (ClassPathPermission) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((action == null) ? 0 : action.hashCode()
				+ ((getName() == null) ? 0 : getName().hashCode()));
		return result;
	}
	
	@Override
	public String toString() {
		return "ClassPathPermission [name=" + getName() + "], [action=" + action + "]";
	}
}
