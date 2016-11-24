let React = require('react');

class Header extends React.Component {
  render() {
    let self = this;

    function createItem([id, text]) {
      return <a key={id}
                className={'item' + (self.props.menuItemIsEnabled[id] ? '' : ' disabled') +
                                    (self.props.menuItemIsActive[id] ? ' active' : '')}
                onClick={self.props.menuItemIsEnabled[id] ? self.props.onMenuItemSelected.bind(null, id) : null}>{text}</a>
    }

    let leftItems = [['home', 'Play'], ['stats', 'Stats'], ['duel', 'Duel']].map(createItem),
        rightItems = [['edit', 'Edit']].map(createItem);

    return (
      <div className='header-component ui secondary pointing menu'>
        {leftItems}
        <div className='right menu'>
          {rightItems}
          <a className='item login' onClick={this.props.onLogin.bind(this.props)}><span>Войти</span></a>
        </div>
      </div>
    );
  }
}

exports.Header = Header;
