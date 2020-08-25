import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISociete, defaultValue } from 'app/shared/model/societe.model';

export const ACTION_TYPES = {
  FETCH_SOCIETE_LIST: 'societe/FETCH_SOCIETE_LIST',
  FETCH_SOCIETE: 'societe/FETCH_SOCIETE',
  CREATE_SOCIETE: 'societe/CREATE_SOCIETE',
  UPDATE_SOCIETE: 'societe/UPDATE_SOCIETE',
  DELETE_SOCIETE: 'societe/DELETE_SOCIETE',
  RESET: 'societe/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISociete>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type SocieteState = Readonly<typeof initialState>;

// Reducer

export default (state: SocieteState = initialState, action): SocieteState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SOCIETE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SOCIETE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_SOCIETE):
    case REQUEST(ACTION_TYPES.UPDATE_SOCIETE):
    case REQUEST(ACTION_TYPES.DELETE_SOCIETE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_SOCIETE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SOCIETE):
    case FAILURE(ACTION_TYPES.CREATE_SOCIETE):
    case FAILURE(ACTION_TYPES.UPDATE_SOCIETE):
    case FAILURE(ACTION_TYPES.DELETE_SOCIETE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_SOCIETE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_SOCIETE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_SOCIETE):
    case SUCCESS(ACTION_TYPES.UPDATE_SOCIETE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_SOCIETE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/societes';

// Actions

export const getEntities: ICrudGetAllAction<ISociete> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SOCIETE_LIST,
  payload: axios.get<ISociete>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ISociete> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SOCIETE,
    payload: axios.get<ISociete>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ISociete> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SOCIETE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISociete> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SOCIETE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISociete> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SOCIETE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
