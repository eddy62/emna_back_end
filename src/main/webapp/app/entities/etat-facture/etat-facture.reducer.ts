import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtatFacture, defaultValue } from 'app/shared/model/etat-facture.model';

export const ACTION_TYPES = {
  FETCH_ETATFACTURE_LIST: 'etatFacture/FETCH_ETATFACTURE_LIST',
  FETCH_ETATFACTURE: 'etatFacture/FETCH_ETATFACTURE',
  CREATE_ETATFACTURE: 'etatFacture/CREATE_ETATFACTURE',
  UPDATE_ETATFACTURE: 'etatFacture/UPDATE_ETATFACTURE',
  DELETE_ETATFACTURE: 'etatFacture/DELETE_ETATFACTURE',
  RESET: 'etatFacture/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtatFacture>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtatFactureState = Readonly<typeof initialState>;

// Reducer

export default (state: EtatFactureState = initialState, action): EtatFactureState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETATFACTURE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETATFACTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETATFACTURE):
    case REQUEST(ACTION_TYPES.UPDATE_ETATFACTURE):
    case REQUEST(ACTION_TYPES.DELETE_ETATFACTURE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETATFACTURE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETATFACTURE):
    case FAILURE(ACTION_TYPES.CREATE_ETATFACTURE):
    case FAILURE(ACTION_TYPES.UPDATE_ETATFACTURE):
    case FAILURE(ACTION_TYPES.DELETE_ETATFACTURE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATFACTURE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATFACTURE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETATFACTURE):
    case SUCCESS(ACTION_TYPES.UPDATE_ETATFACTURE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETATFACTURE):
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

const apiUrl = 'api/etat-factures';

// Actions

export const getEntities: ICrudGetAllAction<IEtatFacture> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETATFACTURE_LIST,
  payload: axios.get<IEtatFacture>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtatFacture> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETATFACTURE,
    payload: axios.get<IEtatFacture>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtatFacture> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETATFACTURE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtatFacture> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETATFACTURE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtatFacture> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETATFACTURE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
