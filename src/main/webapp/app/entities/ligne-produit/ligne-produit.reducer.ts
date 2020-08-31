import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILigneProduit, defaultValue } from 'app/shared/model/ligne-produit.model';

export const ACTION_TYPES = {
  FETCH_LIGNEPRODUIT_LIST: 'ligneProduit/FETCH_LIGNEPRODUIT_LIST',
  FETCH_LIGNEPRODUIT: 'ligneProduit/FETCH_LIGNEPRODUIT',
  CREATE_LIGNEPRODUIT: 'ligneProduit/CREATE_LIGNEPRODUIT',
  UPDATE_LIGNEPRODUIT: 'ligneProduit/UPDATE_LIGNEPRODUIT',
  DELETE_LIGNEPRODUIT: 'ligneProduit/DELETE_LIGNEPRODUIT',
  RESET: 'ligneProduit/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILigneProduit>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type LigneProduitState = Readonly<typeof initialState>;

// Reducer

export default (state: LigneProduitState = initialState, action): LigneProduitState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LIGNEPRODUIT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LIGNEPRODUIT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_LIGNEPRODUIT):
    case REQUEST(ACTION_TYPES.UPDATE_LIGNEPRODUIT):
    case REQUEST(ACTION_TYPES.DELETE_LIGNEPRODUIT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_LIGNEPRODUIT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LIGNEPRODUIT):
    case FAILURE(ACTION_TYPES.CREATE_LIGNEPRODUIT):
    case FAILURE(ACTION_TYPES.UPDATE_LIGNEPRODUIT):
    case FAILURE(ACTION_TYPES.DELETE_LIGNEPRODUIT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIGNEPRODUIT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_LIGNEPRODUIT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_LIGNEPRODUIT):
    case SUCCESS(ACTION_TYPES.UPDATE_LIGNEPRODUIT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_LIGNEPRODUIT):
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

const apiUrl = 'api/ligne-produits';

// Actions

export const getEntities: ICrudGetAllAction<ILigneProduit> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LIGNEPRODUIT_LIST,
  payload: axios.get<ILigneProduit>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ILigneProduit> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LIGNEPRODUIT,
    payload: axios.get<ILigneProduit>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ILigneProduit> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LIGNEPRODUIT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILigneProduit> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LIGNEPRODUIT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILigneProduit> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LIGNEPRODUIT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
