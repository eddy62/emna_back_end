import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEtatDevis, defaultValue } from 'app/shared/model/etat-devis.model';

export const ACTION_TYPES = {
  FETCH_ETATDEVIS_LIST: 'etatDevis/FETCH_ETATDEVIS_LIST',
  FETCH_ETATDEVIS: 'etatDevis/FETCH_ETATDEVIS',
  CREATE_ETATDEVIS: 'etatDevis/CREATE_ETATDEVIS',
  UPDATE_ETATDEVIS: 'etatDevis/UPDATE_ETATDEVIS',
  DELETE_ETATDEVIS: 'etatDevis/DELETE_ETATDEVIS',
  RESET: 'etatDevis/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEtatDevis>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type EtatDevisState = Readonly<typeof initialState>;

// Reducer

export default (state: EtatDevisState = initialState, action): EtatDevisState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ETATDEVIS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ETATDEVIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_ETATDEVIS):
    case REQUEST(ACTION_TYPES.UPDATE_ETATDEVIS):
    case REQUEST(ACTION_TYPES.DELETE_ETATDEVIS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_ETATDEVIS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ETATDEVIS):
    case FAILURE(ACTION_TYPES.CREATE_ETATDEVIS):
    case FAILURE(ACTION_TYPES.UPDATE_ETATDEVIS):
    case FAILURE(ACTION_TYPES.DELETE_ETATDEVIS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATDEVIS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_ETATDEVIS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_ETATDEVIS):
    case SUCCESS(ACTION_TYPES.UPDATE_ETATDEVIS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_ETATDEVIS):
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

const apiUrl = 'api/etat-devis';

// Actions

export const getEntities: ICrudGetAllAction<IEtatDevis> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ETATDEVIS_LIST,
  payload: axios.get<IEtatDevis>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IEtatDevis> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ETATDEVIS,
    payload: axios.get<IEtatDevis>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IEtatDevis> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ETATDEVIS,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEtatDevis> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ETATDEVIS,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEtatDevis> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ETATDEVIS,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
