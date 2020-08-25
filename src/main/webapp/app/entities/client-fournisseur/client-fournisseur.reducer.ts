import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IClientFournisseur, defaultValue } from 'app/shared/model/client-fournisseur.model';

export const ACTION_TYPES = {
  FETCH_CLIENTFOURNISSEUR_LIST: 'clientFournisseur/FETCH_CLIENTFOURNISSEUR_LIST',
  FETCH_CLIENTFOURNISSEUR: 'clientFournisseur/FETCH_CLIENTFOURNISSEUR',
  CREATE_CLIENTFOURNISSEUR: 'clientFournisseur/CREATE_CLIENTFOURNISSEUR',
  UPDATE_CLIENTFOURNISSEUR: 'clientFournisseur/UPDATE_CLIENTFOURNISSEUR',
  DELETE_CLIENTFOURNISSEUR: 'clientFournisseur/DELETE_CLIENTFOURNISSEUR',
  RESET: 'clientFournisseur/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IClientFournisseur>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ClientFournisseurState = Readonly<typeof initialState>;

// Reducer

export default (state: ClientFournisseurState = initialState, action): ClientFournisseurState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CLIENTFOURNISSEUR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CLIENTFOURNISSEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CLIENTFOURNISSEUR):
    case REQUEST(ACTION_TYPES.UPDATE_CLIENTFOURNISSEUR):
    case REQUEST(ACTION_TYPES.DELETE_CLIENTFOURNISSEUR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CLIENTFOURNISSEUR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CLIENTFOURNISSEUR):
    case FAILURE(ACTION_TYPES.CREATE_CLIENTFOURNISSEUR):
    case FAILURE(ACTION_TYPES.UPDATE_CLIENTFOURNISSEUR):
    case FAILURE(ACTION_TYPES.DELETE_CLIENTFOURNISSEUR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLIENTFOURNISSEUR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLIENTFOURNISSEUR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CLIENTFOURNISSEUR):
    case SUCCESS(ACTION_TYPES.UPDATE_CLIENTFOURNISSEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CLIENTFOURNISSEUR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/client-fournisseurs';

// Actions

export const getEntities: ICrudGetAllAction<IClientFournisseur> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CLIENTFOURNISSEUR_LIST,
  payload: axios.get<IClientFournisseur>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IClientFournisseur> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CLIENTFOURNISSEUR,
    payload: axios.get<IClientFournisseur>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IClientFournisseur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CLIENTFOURNISSEUR,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IClientFournisseur> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CLIENTFOURNISSEUR,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IClientFournisseur> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CLIENTFOURNISSEUR,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
