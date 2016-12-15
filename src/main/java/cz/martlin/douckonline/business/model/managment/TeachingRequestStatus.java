package cz.martlin.douckonline.business.model.managment;

/**
 * Status of teaching request: Waiting for reactions, dismissed or yet teached.
 * @author m@rtlin <martlin@seznam.cz>
 */
public enum TeachingRequestStatus {
    WAITING_FOR_REACTIONS, TEACHING_RUNNING, DISMISSED 
}
