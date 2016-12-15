package cz.martlin.douckonline.business.model.lector;

/**
 * Suitability of lector to subject. Subject can be lectors primary, secondary,
 * or only if noone else is more suitable.
 *
 * @author m@rtlin <martlin@seznam.cz>
 */
public enum Suitability {
    PRIMARY_SUBJECT, SECONDARY_SUBJECT, IF_NO_ONE_ELSE
}
